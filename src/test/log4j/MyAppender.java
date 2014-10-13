package test.log4j;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * 解决error输出也会出现在info.log的问题
 * 只有当Threshold与priority一致时，才进行输出，就实现了真正Log4j按照级别输出日志文件
 * 
 * @author zhang.rui
 * 
 */
public class MyAppender extends DailyRollingFileAppender {

	@Override
	public boolean isAsSevereAsThreshold(Priority priority) {
		//System.out.println(this.getName() + "=============" + priority + "==========" + this.getThreshold());
		// 只判断是否相等，而不判断优先级
		return this.getThreshold().equals(priority);
	}
}
