package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LimitSwitchCommand extends Command {

    public LimitSwitchCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.limitSwitch);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	while (Robot.limitSwitch.get()) {
//    		Timer.delay(10);
//    		
//    	}
    	
    	//boolean switchResult = Robot.limitSwitch.get();
    	//System.out.println("switchResult:" + switchResult);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isFinished = Robot.limitSwitch.get();
    	if (isFinished) {
    		System.out.println("finished with limit switch");
    	}
        return isFinished;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.limitSwitch.get();
    }
}
