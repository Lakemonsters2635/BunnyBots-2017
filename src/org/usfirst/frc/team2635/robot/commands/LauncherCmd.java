package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LauncherCmd extends Command {

    public LauncherCmd() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.launcher);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.launcher.startLauncher(2500);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.launcher.startLauncher(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.launcher.startLauncher(0);
    }
}
