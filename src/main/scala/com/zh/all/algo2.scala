//package com.zh.all
//
//import org.joda.time.{DateTime, Days}
//import System.out
//object algo2 {
//
//
//    def main(args: Array[String]): Unit = {
//
//        val day=new DateTime()
//        val week=day.dayOfWeek()
//        val month=day.dayOfMonth()
//        val era=day.centuryOfEra()
//
//       val preday= day.minusDays(10)
//        val nextday=day.plusDays(4)
//        println(day)
////        println(week.getAsText)
////        println(month.getAsText)
////        println(era.getAsText)
////        println(preday)
////        println(nextday)
////        println(day.dayOfYear().getAsText)
////        println(day.monthOfYear().getAsText)
////        println(day.secondOfDay().getAsText)
////        println(day.weekOfWeekyear().getAsText)
////        println(day.weekyear().getAsText)
////        val dayz:Days=Days.daysBetween(day,nextday)
////        println(dayz.getDays)
//
//        val matrixCols =Seq(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,22,29,36,43,57,71,85,119,147,175,203,231,259,287,315,343,365)
//        val origMatCols = if (matrixCols(0) > 0) 0 +: matrixCols else matrixCols
////        origMatCols.foreach(println)
//        val matrixIdx2ColsMap = origMatCols.indices.map {
//            idx =>
//                val endIdx = if (idx > origMatCols.length - 1) origMatCols.length - 1 else idx + 2
//                val g = origMatCols.slice(idx, endIdx)
//                val startNum = g(0)
//
//                val endNum =
//                    if (g.length > 1) g(1) - 1 else g(0)
//                (idx, (startNum, endNum))
//
//        }.sortBy(_._1)
//        matrixIdx2ColsMap.foreach(line=> println(line._1 +" "+line._2._1+ " "+line._2._2))
//
//        val weekendDays=Seq(5,6,7).toArray
//        val hotelCds=Seq(  1,
//        10,
//        105,
//        12,
//        14,
//        144,
//        17,
//        2,
//        206,
//        25,
//        251,
//        278)
//        val  startDt=new DateTime()
//        val fcDateTags = hotelCds.flatMap {
//            htl => (0 until 120).map {
//                day =>
//                    val dt = startDt.plusDays(day + 1)
//                    val tag = dt2Tag(dt, seasonRaw, holidayRaw, seasonWeekTagType, weekendDays, htl)
//                    ((tag._1, dt.toString("yyyyMMdd").toInt), tag._2)
//            }
//        }.toMap
//
//        val specDaysFcDate = fcDateTags.filter(_._2._2.isDefined).toArray.map{
//            case ((htl, dt), _) =>
//                (htl, dt)
//        }
//    }
//
//
//
//    private def dt2Tag(lvdt: DateTime,
//                       seasonRows: Array[Array[String]],
//                       specialDaysRows: Array[Array[String]],
//                       seasonWeekTagType: String,
//                       weekendDays: Array[Int],
//                       htlcd: String) = {
//        val lvdtNum = lvdt.toString("yyyyMMdd").toInt
//        val lvdtStr = lvdt.toString("yyyy-MM-dd")
//        val weekDay = lvdt.getDayOfWeek
//        implicit val ordrMinSeason = Ordering.by {
//            (r: Array[String]) =>
//                val start = r(seasonColIdx("start_dt")).replaceAll("-", "").toInt
//                val end = r(seasonColIdx("end_dt")).replaceAll("-", "").toInt
//                end - start
//        }
//
//        val season = seasonRows.filter {
//            r =>
//                val htl = r(seasonColIdx("htl_cd"))
//                htl == htlcd
//        }.filter {
//            r =>
//                val startDt = r(seasonColIdx("start_dt"))
//                val endDt = r(seasonColIdx("end_dt"))
//                val startNum = startDt.replaceAll("-", "").toInt
//                val endNum = endDt.replaceAll("-", "").toInt
//                lvdtNum >= startNum && lvdtNum <= endNum
//        }.min(ordrMinSeason)
//
//        val special = specialDaysRows.filter {
//            r =>
//                lvdtStr == r(holidaysColIdx("date"))
//        }
//
//        val seasonNum = season(seasonColIdx("season")).toInt
//        val seasonWeekTag = seasonWeekTagType match {
//            case "weekendOrNot" => seasonWeekday2Tag(seasonNum, weekDay, weekendDays)
//            case "noWeekday" => seasonWeekday2Tag(seasonNum)
//            case _ => seasonWeekday2Tag(seasonNum, weekDay)
//        }
//
//        val specialTag = if (special.isEmpty) None else Some(special.map(r => r(holidaysColIdx("tag")).toInt))
//        (htlcd, (seasonWeekTag, specialTag))
//    }
//
//    private def seasonWeekday2Tag(seasonNum: Int) = {
//        (seasonNum + 1) * 10
//    }
//
//    private def seasonWeekday2Tag(seasonNum: Int, weekday: Int, weekendDays: Array[Int]) = {
//        val dayTag = if (weekendDays.contains(weekday)) 1 else 2
//        (seasonNum + 1) * 10 + dayTag
//    }
//
//    private def seasonWeekday2Tag(seasonNum: Int, weekday: Int) = {
//        (seasonNum + 1) * 10 + weekday
//    }
//
//    def advBkPeriod2ColIdx(origAdvBkPeriod: Int, matrixIdx2ColsMap: IndexedSeq[(Int, (Int, Int))]) = {
//        val maxCol = matrixIdx2ColsMap.last._2._2
//        val advBkPeriod = if (origAdvBkPeriod >= maxCol) maxCol else origAdvBkPeriod
//        val res = matrixIdx2ColsMap.filter {
//            case (colIdx, (startPeriod, endPeriod)) =>
//                advBkPeriod >= startPeriod && advBkPeriod <= endPeriod
//        }
//
//        if (res.isEmpty) {
//            val msg = s"Cannot find colIdx from matrixIdx2ColsMap, origAdvBkPeriod = $origAdvBkPeriod, advBkPeriod = $advBkPeriod"
//
//            println(msg)
//        }
//
//        res.head._1
//    }
//
//    private def segBkDailySumColIdx(colName: String) = colName match {
//        case "htl_cd" => 0
//        case "seg_cd" => 1
//        case "is_member" => 2
//        case "live_dt" => 3
//        case "adv_bk_days" => 4
//        case "rns" => 5
//        case "rev" => 6
//    }
//
//    private def otbColindex(colName: String) = colName match {
//        case "city_id" => 0
//        case "star" => 1
//        case "remark" => 2
//        case "live_dt" => 3
//        case "adv_days" => 4
//        case "rns" => 5
//        case "rev" => 6
//    }
//    private def seasonColIdx(colName: String) = colName match {
//        case "htl_cd" => 0
//        case "season" => 1
//        case "base_rt_cd" => 2
//        case "base_rt_price" => 3
//        case "month_no" => 4
//        case "start_dt" => 5
//        case "end_dt" => 6
//        case "update_dt" => 7
//    }
//
//    private def cityColIdx(colName: String) = colName match {
//        case "city_id" => 0
//    }
//
//    private def segPredConfColIdx(colName: String) = colName match {
//        case "id" => 0
//        case "htl_cd" => 1
//        case "seg_cd" => 2
//        case "dt_tag" => 3
//        case "adv_fc_days" => 4
//        case "is_predict" => 5
//        case "predit_para_m" => 6
//        case "predit_idx_w" => 7
//        case "comb_idx" => 8
//    }
//
//    private def holidaysColIdx(colName: String) = colName match {
//        case "desp" => 0
//        case "date" => 1
//        case "tag" => 2
//        case "year" => 3
//        case "holiday_key" => 4
//        case "offset" => 5
//    }
//
//    private def invPercentColIdx(colName: String) = colName match {
//        case "htl_cd" => 0
//        case "total_inv" => 1
//        case "current_inv" => 2
//        case "startdt" => 3
//        case "enddt" => 4
//    }
//
//
//}
