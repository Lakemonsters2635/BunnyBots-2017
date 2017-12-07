package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;
import org.usfirst.frc.team2635.robot.RobotMap;
import org.usfirst.frc.team2635.robot.model.MotionParameters;
import org.usfirst.frc.team2635.robot.model.MotionProfileLibrary;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightMotionMagic extends Command {
	public double driveDistance;
	public boolean reverse;
	double rpm;
	
	MotionParameters driveParams;
    public DriveStraightMotionMagic(double rpm, double distance, boolean reverse) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.rpm = rpm;
    	this.driveDistance = distance;
    	this.reverse = reverse;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveParams = MotionProfileLibrary.getDriveParameters(RobotMap.WHEEL_RADIUS_INCHES, driveDistance, rpm, reverse);
    	
    	Robot.drive.initMotionMagic();
    	Robot.drive.setMotionMagicPID(
    			RobotMap.DRIVE_STRAIGHT_MOTION_MAGIC_P,
    			RobotMap.DRIVE_STRAIGHT_MOTION_MAGIC_I,
    			RobotMap.DRIVE_STRAIGHT_MOTION_MAGIC_D,
    			RobotMap.DRIVE_STRAIGHT_MOTION_MAGIC_F);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.driveStraightMotionMagic(driveParams);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean done = Robot.drive.motionMagicDone(driveParams, Robot.drive.DRIVE_ERROR_TOLERANCE);
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
