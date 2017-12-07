package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.RobotMap;
import org.usfirst.frc.team2635.robot.commands.DriveCommand;
import org.usfirst.frc.team2635.robot.model.MotionParameters;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class Drive extends Subsystem {
	CANTalon frontLeftMotor;
	CANTalon rearLeftMotor;
	CANTalon frontRightMotor;
	CANTalon rearRightMotor;
	RobotDrive drive;
	DriveCommand teleopCommand; 
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Joystick rightStick;
	Joystick leftStick;
	
	public static final double DRIVE_ERROR_TOLERANCE = 0.03;
	
	public Drive(Joystick leftStick,Joystick rightStick) {
		frontLeftMotor = new CANTalon(RobotMap.DRIVE_LEFT_FRONT);
		frontRightMotor = new CANTalon(RobotMap.DRIVE_RIGHT_FRONT);
		rearLeftMotor = new CANTalon(RobotMap.DRIVE_LEFT_BACK);
		rearRightMotor = new CANTalon(RobotMap.DRIVE_RIGHT_BACK);
		drive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		this.leftStick = leftStick;
		this.rightStick = rightStick;
		
		frontLeftMotor.changeControlMode(TalonControlMode.PercentVbus);
		frontRightMotor.changeControlMode(TalonControlMode.PercentVbus);
		rearLeftMotor.changeControlMode(TalonControlMode.PercentVbus);
		rearRightMotor.changeControlMode(TalonControlMode.PercentVbus);

	}
	public void tankDrive(double left, double right) {
		drive.tankDrive(-left, -right);
	}
	
    public void initDefaultCommand() {
		System.out.println("Init default command");
		teleopCommand = new DriveCommand(leftStick, rightStick);
		setDefaultCommand(teleopCommand);
    }
    
    public void driveForward(double speed) {
    	frontLeftMotor.set(speed);
    	rearLeftMotor.set(speed);
    	frontRightMotor.set(-speed);
    	frontRightMotor.set(-speed);
    }
    
    public void stopDriving() {
    	frontLeftMotor.set(0);
    	rearLeftMotor.set(0);
    	frontRightMotor.set(0);
    	frontRightMotor.set(0);
    }
    
    public void initMotionMagic() {
    	rearRightMotor.changeControlMode(TalonControlMode.Follower);
    	rearRightMotor.set(frontRightMotor.getDeviceID());
    	
    	rearLeftMotor.changeControlMode(TalonControlMode.Follower);
    	rearLeftMotor.set(frontLeftMotor.getDeviceID());
    	
    	frontRightMotor.changeControlMode(TalonControlMode.MotionMagic);
    	frontLeftMotor.changeControlMode(TalonControlMode.MotionMagic);
    	
    	frontRightMotor.setPosition(0.0);
		frontLeftMotor.setPosition(0.0);
    }
    
    public void setMotionMagicPID(double p, double i, double d, double f) {
    	frontRightMotor.setPID(p, i, d);
    	frontRightMotor.setF(f);
    	frontLeftMotor.setPID(p, i, d);
    	frontLeftMotor.setF(f);
    }
    
    public void driveStraightMotionMagic(MotionParameters  driveParams) {
		frontRightMotor.setMotionMagicCruiseVelocity(driveParams.rightVelocity);
		frontLeftMotor.setMotionMagicCruiseVelocity(driveParams.leftVelocity);
		
		frontRightMotor.setMotionMagicAcceleration(driveParams.rightAcceleration);
		frontLeftMotor.setMotionMagicAcceleration(driveParams.leftAcceleration);
		
		frontRightMotor.set(driveParams.rightWheelRotations);
		frontLeftMotor.set(driveParams.leftWheelRotations);
	}
    
    public boolean motionMagicDone(MotionParameters rotationParams, double errorTolerance) {

		double rightFrontPosition = frontRightMotor.getPosition();
		double leftFrontPosition = frontLeftMotor.getPosition();
		
		double rightFrontError = Math.abs(rotationParams.rightWheelRotations - rightFrontPosition);
		double leftFrontError = Math.abs(rotationParams.leftWheelRotations - leftFrontPosition);
		
		boolean isDone = (rightFrontError < errorTolerance && leftFrontError < errorTolerance);
		
		return isDone;
    }
}

