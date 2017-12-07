package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class ClampIn extends TimedCommand {

    public ClampIn(double timeout) {
        super(timeout);
        requires(Robot.lifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("ClampIn Command");
    	Robot.lifter.clampIn();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Called once after timeout
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
