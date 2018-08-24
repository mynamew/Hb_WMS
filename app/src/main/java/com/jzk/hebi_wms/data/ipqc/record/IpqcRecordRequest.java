package com.jzk.hebi_wms.data.ipqc.record;
/** 
  * 抽检记录的请求
  * @author   jzk
  * create at: 2018/8/24 13:33
  */  
public class IpqcRecordRequest {

    /**
     * LotNo : IPQC201808080001
     * PlanDateEnd : 2018-08-24
     * PlanDateStart : 2018-08-01
     * Process : MD
     * TimePerod : 08-10
     */

    private String LotNo;
    private String PlanDateEnd;
    private String PlanDateStart;
    private String Process;
    private String TimePerod;

    public String getLotNo() {
        return LotNo;
    }

    public void setLotNo(String LotNo) {
        this.LotNo = LotNo;
    }

    public String getPlanDateEnd() {
        return PlanDateEnd;
    }

    public void setPlanDateEnd(String PlanDateEnd) {
        this.PlanDateEnd = PlanDateEnd;
    }

    public String getPlanDateStart() {
        return PlanDateStart;
    }

    public void setPlanDateStart(String PlanDateStart) {
        this.PlanDateStart = PlanDateStart;
    }

    public String getProcess() {
        return Process;
    }

    public void setProcess(String Process) {
        this.Process = Process;
    }

    public String getTimePerod() {
        return TimePerod;
    }

    public void setTimePerod(String TimePerod) {
        this.TimePerod = TimePerod;
    }
}
