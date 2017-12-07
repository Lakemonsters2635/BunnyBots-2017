package org.usfirst.frc.team2635.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static final int LIMIT_SWITCH_IO_CHANNEL = 2; 
	
	public static final int DRIVE_RIGHT_FRONT = 2;
	public static final int DRIVE_RIGHT_BACK = 1;
	public static final int DRIVE_LEFT_FRONT = 4;
	public static final int DRIVE_LEFT_BACK = 3;
	//public static final int LAUNCHER_0 = 1;
	//public static final int LAUNCHER_1 = 11;
	
	//FHE TODO: BAD
	public static final int LAUNCHER_0 = 6;
	public static final int LAUNCHER_1 = 5;
	
	public static final int ORIENTER_0 = 7;
	public static final int ORIENTER_1 = 8;
	public static final int FEEDER = 9;
	
	public static final int WHEEL_RADIUS_INCHES = 3;
	
	public static final int PIKE_CHANNEL_0 = 1;
	public static final int PIKE_CHANNEL_1 = 0;
	
	public static final int LIFT_CHANNEL_0 = 4;
	public static final int LIFT_CHANNEL_1 = 3;
	
	public static final int GRAB_CHANNEL_0 = 2;
	public static final int GRAB_CHANNEL_1 = 5;
	
	public static final double DRIVE_STRAIGHT_MOTION_MAGIC_F = 1.5;
	public static final double DRIVE_STRAIGHT_MOTION_MAGIC_P = 10;
    public static final double DRIVE_STRAIGHT_MOTION_MAGIC_I =  0.0004; 
    public static final double DRIVE_STRAIGHT_MOTION_MAGIC_D = 0;

//---------------------BUTTONS-------------------------------------------------------
	
	public static final int LIFT_UP_BUTTON = 4;
	public static final int LIFT_DOWN_BUTTON = 5;
//	public static final int CLAMP_IN_BUTTON = 3;
//	public static final int CLAMP_OUT_BUTTON = 4;
	public static final int LAUNCHER_BUTTON = 1;
	
	public static final int PIKE_BUTTON = 3;
	
	public static final int RIGHT_JOYSTICK = 1;
	public static final int LEFT_JOYSTICK = 0;
	public static final int LAUNCHER_SPEED_AXIS = 2;
	
	public static final int LIMIT_SWITCH_BUTTON = 7;
	
	public static double LauncherP = 0.05;
	public static double LauncherI = 0;
	public static double LauncherD = 0;
	public static double LauncherF = 0.02;
	public static double LauncherSetpoint = 4000;
}
