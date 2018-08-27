package com.jzk.hebi_wms.data.ipqc;
/**
  * 获取抽检总数的请求
  * @author   jzk
  * create at: 2018/8/20 14:45
  */
public class CalculateCheckCountRequest {

    /**
     * PlanDate : 2018-08-01
     * Process : MD
     * TimePerod : 10-12
     */

    private String PlanDate;
    private String Process;
    private String TimePerod;

    public String getPlanDate() {
        return PlanDate;
    }

    public void setPlanDate(String PlanDate) {
        this.PlanDate = PlanDate;
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
