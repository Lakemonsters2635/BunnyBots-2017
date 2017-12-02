package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2635.robot.commands.*;
import org.usfirst.frc.team2635.robot.model.CommandGroupLibrary;
import org.usfirst.frc.team2635.robot.subsystems.*;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

//Camera base is : 20.415 inches.


public class Robot extends IterativeRobot {
	Joystick leftStick;
	Joystick rightStick;

	public static OI oi;

	public static Drive drive;
	public static Lifter lifter;
	public static Launcher launcher;
	public static Pike pike;
	public static LimitSwitch limitSwitch;
	
	DriveCommand driveCommand;
	PikeCommand pikeCommand;
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	SmartDashboard dashboard;
	LiftUp liftUp;
	ClampOut clampOut;

	
	public static boolean lifterOpen;
	
	CANTalon flywheel0;
	CANTalon flywheel1;
	CANTalon orienter0;
	CANTalon orienter1;
	CANTalon feeder;
	
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
		lifter = new Lifter();
		launcher = new Launcher();
		pike = new Pike();
		limitSwitch = new LimitSwitch();
		
		driveCommand = new DriveCommand(leftStick, rightStick);
		pikeCommand = new PikeCommand();
		
//		oi.liftUpButton.whenPressed(new LiftUp(2));
//		oi.liftDownButton.whenPressed(new LiftDown(2));
//		
//		oi.clampInButton.whenPressed(new ClampIn(2));
//		oi.clampOutButton.whenPressed(new ClampOut(2));
		
		oi.liftOpenButton.whenPressed(CommandGroupLibrary.lifterOpen());
		oi.liftClosedButton.whenPressed(CommandGroupLibrary.lifterClosed());
		//oi.pikeButton.whenPressed(pikeCommand);
		oi.pikeButton.toggleWhenPressed(pikeCommand);
		oi.limitSwitchButton.whileHeld(new LimitSwitchCommand());
		
		//oi.revUpButton.whileHeld(new LauncherCmd());
		
		lifterOpen = true;
		
		SmartDashboard.putDouble("Launcher Output0", 0);
		SmartDashboard.putDouble("Launcher Output1", 0);
		
//		SmartDashboard.putDouble("Launcher P Value", RobotMap.LauncherP);
//		SmartDashboard.putDouble("Launcher I Value", RobotMap.LauncherI);
//		SmartDashboard.putDouble("Launcher D Value", RobotMap.LauncherD);
//		SmartDashboard.putDouble("Launcher F Value", RobotMap.LauncherF);
//		SmartDashboard.putDouble("Launcher Setpoint Value", RobotMap.LauncherSetpoint);
		
		//System.out.println("Flywheel 0 sensor present: " + launcher.flywheel0.isSensorPresent(CANTalon.FeedbackDevice.CtreMagEncoder_Relative));
		//System.out.println("Flywheel 1 sensor present: " + launcher.flywheel1.isSensorPresent(CANTalon.FeedbackDevice.CtreMagEncoder_Relative));
//		
//		RobotMap.LauncherP = SmartDashboard.getDouble("Launcher P Value");
//		RobotMap.LauncherI = SmartDashboard.getDouble("Launcher I Value");
//		RobotMap.LauncherD = SmartDashboard.getDouble("Launcher D Value");
//		RobotMap.LauncherF = SmartDashboard.getDouble("Launcher F Value");
//		RobotMap.LauncherSetpoint = SmartDashboard.getDouble("Launcher Setpoint Value");
		
		flywheel0 = new CANTalon(RobotMap.LAUNCHER_0); //TODO: Set everything to real things
		flywheel1 = new CANTalon(RobotMap.LAUNCHER_1);
		
		flywheel0.changeControlMode(TalonControlMode.PercentVbus);
		flywheel0.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		
		flywheel1.changeControlMode(TalonControlMode.PercentVbus);
		flywheel1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		
		launcher.setPIDF(RobotMap.LauncherP, RobotMap.LauncherI, RobotMap.LauncherD, RobotMap.LauncherF);
		launcher.setSetpoint(RobotMap.LauncherSetpoint);
		
		orienter0 = new CANTalon(RobotMap.ORIENTER_0);
		orienter1 = new CANTalon(RobotMap.ORIENTER_1);
		feeder = new CANTalon(RobotMap.FEEDER);
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
		

		
		//CommandGroupLibrary.lifterInit().start();
		
		driveCommand.start();
		
		
//		launcher.setPID(
//				SmartDashboard.getDouble("Launcher P Value"), 
//				SmartDashboard.getDouble("Launcher I Value"), 
//				SmartDashboard.getDouble("Launcher D Value"),
//				SmartDashboard.getDouble("Launcher F Value")
//				);
		
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		//double setPoint = SmartDashboard.getDouble("Launcher Setpoint Value");
		//System.out.println("setPoint : " + setPoint);
		
		//launcher.startLauncher(setPoint);
//		LiftDown liftDown = new LiftDown(2);
//		liftDown.start();
//		LiftUp liftUp = new LiftUp(2);
//		liftUp.start();
//		
//		ClampIn clampIn = new ClampIn(2);
//		clampIn.start();
//		ClampOut clampOut= new ClampOut(2);
//		clampOut.start();
		
		if(leftStick.getRawButton(RobotMap.LAUNCHER_BUTTON)) {
			flywheel0.set(-launcher.calculateSpeed(-flywheel0.getSpeed()));
			flywheel1.set(launcher.calculateSpeed(flywheel1.getSpeed()));
			
			orienter0.set(0.25);
			orienter1.set(-0.25);
			
			feeder.set(0.5);
			
		} else {
			flywheel0.set(0);
			flywheel1.set(0);
		}
		//SmartDashboard.putDouble("Launcher Output0", flywheel0.getSpeed());
		//SmartDashboard.putDouble("Launcher Output1", flywheel1.getSpeed());
		
		

//		RobotMap.LauncherP = SmartDashboard.getDouble("Launcher P Value");
//		RobotMap.LauncherI = SmartDashboard.getDouble("Launcher I Value");
//		RobotMap.LauncherD = SmartDashboard.getDouble("Launcher D Value");
//		RobotMap.LauncherF = SmartDashboard.getDouble("Launcher F Value");
		//RobotMap.LauncherSetpoint = SmartDashboard.getDouble("Launcher Setpoint Value");
		
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
