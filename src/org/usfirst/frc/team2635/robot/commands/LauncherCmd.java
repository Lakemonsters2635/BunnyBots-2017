package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;
import org.usfirst.frc.team2635.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */


public class LauncherCmd extends Command {
	CANTalon flywheel0;
	CANTalon flywheel1;
	CANTalon orienter0;
	CANTalon orienter1;
	CANTalon feeder;
	
	    public LauncherCmd() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.launcher);
    	flywheel0 = new CANTalon(RobotMap.LAUNCHER_0); //TODO: Set everything to real things
		flywheel1 = new CANTalon(RobotMap.LAUNCHER_1);
		
		flywheel0.changeControlMode(TalonControlMode.PercentVbus);
		flywheel0.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		
		flywheel1.changeControlMode(TalonControlMode.PercentVbus);
		flywheel1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		
		Robot.launcher.setPIDF(RobotMap.LauncherP, RobotMap.LauncherI, RobotMap.LauncherD, RobotMap.LauncherF);
		Robot.launcher.setSetpoint(RobotMap.LauncherSetpoint);
		
		orienter0 = new CANTalon(RobotMap.ORIENTER_0);
		orienter1 = new CANTalon(RobotMap.ORIENTER_1);
		feeder = new CANTalon(RobotMap.FEEDER);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	flywheel0.set(-Robot.launcher.calculateSpeed(-flywheel0.getSpeed()));
		flywheel1.set(Robot.launcher.calculateSpeed(flywheel1.getSpeed()));
		
		orienter0.set(0.15);
		orienter1.set(0.15);
		
		feeder.set(0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		double smartDashBoardspeed = SmartDashboard.getDouble("Launcher Setpoint Value");

    	
    	//Robot.launcher.startLauncher(smartDashBoardspeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.launcher.startLauncher(0);
    	flywheel0.set(0);
		flywheel1.set(0);

		orienter0.set(0);
		orienter1.set(0);
		
		feeder.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//Robot.launcher.startLauncher(0);
    	end();
    }
}
