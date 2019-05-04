package org.jeecgframework.workflow.pojo.activiti;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 流程节点实例历史表
 */
@Entity
@Table(name = "act_hi_actinst")
public class ActHiActinst implements java.io.Serializable {

	// Fields

	private String id;//主键ID
	private String procDefId;//流程定义ID(流程名：版本号：自增ID)
	private String procInstId; //流程实例ID
	private String executionId;//流程运行时ID
	private String actId;//节点编码
	private String taskId;//任务表ID
	private String callProcInstId;//调用流程实例ID
	private String actName;//节点名称
	private String actType;//节点类型
	private String assignee;//执行人
	private Timestamp startTime;//节点开始时间
	private Timestamp endTime;//节点结束时间
	private Long duration;//持续时间
	private String bpmBizTitle;
	private String owner;
	private ActReProcdef prodef;//流程定义实体
	private ActHiProcinst proInsl;//流程实例实体

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "id_", unique = true, nullable = false, length = 64)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "proc_def_id_", nullable = false, length = 64)
	public String getProcDefId() {
		return this.procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}

	@Column(name = "proc_inst_id_", nullable = false, length = 64)
	public String getProcInstId() {
		return this.procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	@Column(name = "execution_id_", nullable = false, length = 64)
	public String getExecutionId() {
		return this.executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	@Column(name = "act_id_", nullable = false)
	public String getActId() {
		return this.actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	@Column(name = "task_id_", length = 64)
	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Column(name = "call_proc_inst_id_", length = 64)
	public String getCallProcInstId() {
		return this.callProcInstId;
	}

	public void setCallProcInstId(String callProcInstId) {
		this.callProcInstId = callProcInstId;
	}

	@Column(name = "act_name_")
	public String getActName() {
		return this.actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	@Column(name = "act_type_", nullable = false)
	public String getActType() {
		return this.actType;
	}

	public void setActType(String actType) {
		this.actType = actType;
	}

	@Column(name = "assignee_", length = 64)
	public String getAssignee() {
		return this.assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	@Column(name = "start_time_", nullable = false, length = 29)
	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time_", length = 29)
	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Column(name = "duration_")
	public Long getDuration() {
		return this.duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
	@Transient
	public String getBpmBizTitle() {
		return bpmBizTitle;
	}

	public void setBpmBizTitle(String bpmBizTitle) {
		this.bpmBizTitle = bpmBizTitle;
	}
	@Transient
	public String getowner() {
		return owner;
	}

	public void setowner(String owner) {
		this.owner = owner;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="proc_def_id_",insertable=false,updatable=false)
	public ActReProcdef getProdef() {
		return prodef;
	}

	public void setProdef(ActReProcdef prodef) {
		this.prodef = prodef;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="proc_inst_id_",insertable=false,updatable=false)
	public ActHiProcinst getProInsl() {
		return proInsl;
	}

	public void setProInsl(ActHiProcinst proInsl) {
		this.proInsl = proInsl;
	}
	
	@Transient
	public String getDurationStr(){
		return dealTimeFromNum(this.duration);
	}
	/**
	* 时间的处理
	* @param time
	* @return
	*/
	public  String dealTimeFromNum(long time) {
		StringBuilder result = new StringBuilder();
		long interval = time / 1000;
		final long day = 24 * 60 * 60;
		final long hour = 60 * 60;
		final long minute = 60;
		int detailDay = 0;
		int detailHour = 0;
		int detailMinute = 0;
		int detailSecond = 0;
		if (interval >= day) {
			detailDay = (int) (interval / day);
			interval = interval - detailDay * day;
		}
		if (interval >= hour) {
			detailHour = (int) (interval / hour);
			interval = interval - hour * detailHour;
		}
		if (interval >= minute) {
			detailMinute = (int) (interval / minute);
			interval = interval - detailMinute * minute;
		}
		if (interval > 0) {
			detailSecond = (int) interval;
		}
		result.setLength(0);
		if (detailDay > 0) {
			result.append(detailDay);
			result.append("天");
		}
		if (detailHour > 0) {
			result.append(detailHour);
			result.append("小时");
		}
		if (detailMinute > 0) {
			result.append(detailMinute);
			result.append("分");
		}
		if (detailSecond > 0) {
			result.append(detailSecond);
			result.append("秒");
		}
		return result.toString();
	}
}