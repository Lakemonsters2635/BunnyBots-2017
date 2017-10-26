package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.RobotMap;
import org.usfirst.frc.team2635.robot.commands.DriveCommand;

import com.ctre.CANTalon;

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
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Joystick rightStick;
	Joystick leftStick;
	public Drive(Joystick leftStick,Joystick rightStick) {
		frontLeftMotor = new CANTalon(RobotMap.DRIVE_LEFT_FRONT);
		frontRightMotor = new CANTalon(RobotMap.DRIVE_RIGHT_FRONT);
		rearLeftMotor = new CANTalon(RobotMap.DRIVE_LEFT_BACK);
		rearRightMotor = new CANTalon(RobotMap.DRIVE_RIGHT_BACK);
		
		drive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		this.leftStick = leftStick;
		this.rightStick = rightStick;
	}
	public void tankDrive(double left, double right) {
		drive.tankDrive(-left, -right);
	}
    public void initDefaultCommand() {
        //setDefaultCommand(new DriveCommand(leftStick, rightStick));
    }
}

