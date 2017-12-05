package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightCommand extends Command {
	double speed;
	double time;
	Timer timer;
	boolean isFinished;
	
    public DriveStraightCommand(double speed, double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	requires(Robot.limitSwitch);
    	this.speed = speed;
    	this.time = time;
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.drive.driveForward(speed);
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.driveForward(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean limit = Robot.limitSwitch.get();
    	boolean timePassed = timer.hasPeriodPassed(time);
    	System.out.println(limit + " " + timePassed);
    	return (limit|| timePassed);

    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.stopDriving();
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
