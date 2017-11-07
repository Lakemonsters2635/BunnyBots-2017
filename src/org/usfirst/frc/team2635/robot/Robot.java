
package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2635.robot.commands.DriveCommand;
import org.usfirst.frc.team2635.robot.subsystems.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Joystick leftStick;
	Joystick rightStick;

	public static OI oi;

	public static Drive drive;
	//public static Lifter lifter;
	public static Launcher launcher;
	
	DriveCommand driveCommand;
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	SmartDashboard dashboard;
	
	public int liftState;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		liftState = 0;
		
		oi = new OI();
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		
		leftStick = new Joystick(RobotMap.LEFT_JOYSTICK);
		rightStick = new Joystick(RobotMap.RIGHT_JOYSTICK);
		
		drive = new Drive(leftStick, rightStick);
		//lifter = new Lifter();
		launcher = new Launcher();
		
		driveCommand = new DriveCommand(leftStick, rightStick);
		
		//dashboard = new SmartDashboard();
		
		
		
		
//		SmartDashboard.putDouble("Launcher Output0", launcher.pidOutput0.getOutput());
//		SmartDashboard.putDouble("Launcher Output1", launcher.pidOutput1.getOutput());
//		SmartDashboard.putDouble("Launcher Input0", launcher.pidSource0.pidGet());
//		SmartDashboard.putDouble("Launcher Input1", launcher.pidSource1.pidGet());
		
//		SmartDashboard.putDouble("Launcher P Value", RobotMap.LauncherP);
//		SmartDashboard.putDouble("Launcher I Value", RobotMap.LauncherI);
//		SmartDashboard.putDouble("Launcher D Value", RobotMap.LauncherD);
//		SmartDashboard.putDouble("Launcher F Value", RobotMap.LauncherF);
//		SmartDashboard.putDouble("Launcher Setpoint Value", RobotMap.LauncherSetpoint);
//		
//		RobotMap.LauncherP = SmartDashboard.getDouble("Launcher P Value");
//		RobotMap.LauncherI = SmartDashboard.getDouble("Launcher I Value");
//		RobotMap.LauncherD = SmartDashboard.getDouble("Launcher D Value");
//		RobotMap.LauncherF = SmartDashboard.getDouble("Launcher F Value");
//		RobotMap.LauncherSetpoint = SmartDashboard.getDouble("Launcher Setpoint Value");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		driveCommand.start();
		
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
//		SmartDashboard.putDouble("Launcher Output0", launcher.pidOutput0.getOutput());
//		SmartDashboard.putDouble("Launcher Output1", launcher.pidOutput1.getOutput());
//		SmartDashboard.putDouble("Launcher Input0", launcher.pidSource0.pidGet());
//		SmartDashboard.putDouble("Launcher Input1", launcher.pidSource1.pidGet());
//		
//		RobotMap.LauncherP = SmartDashboard.getDouble("Launcher P Value");
//		RobotMap.LauncherI = SmartDashboard.getDouble("Launcher I Value");
//		RobotMap.LauncherD = SmartDashboard.getDouble("Launcher D Value");
//		RobotMap.LauncherF = SmartDashboard.getDouble("Launcher F Value");
//		RobotMap.LauncherSetpoint = SmartDashboard.getDouble("Launcher Setpoint Value");
		launcher.startLauncher(RobotMap.LauncherSetpoint * rightStick.getRawAxis(RobotMap.LAUNCHER_SPEED_AXIS));
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
