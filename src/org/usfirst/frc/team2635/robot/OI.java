package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	public final Joystick rightJoystick = new Joystick(RobotMap.RIGHT_JOYSTICK);
	public final Joystick leftJoystick = new Joystick(RobotMap.LEFT_JOYSTICK);
//	public final Button liftUpButton = new JoystickButton(rightJoystick, RobotMap.LIFT_UP_BUTTON);
//	public final Button liftDownButton = new JoystickButton(rightJoystick, RobotMap.LIFT_DOWN_BUTTON);
//	public final Button clampInButton = new JoystickButton(rightJoystick, RobotMap.CLAMP_IN_BUTTON);
//	public final Button clampOutButton = new JoystickButton(rightJoystick, RobotMap.CLAMP_OUT_BUTTON);
	public final Button liftOpenButton = new JoystickButton(rightJoystick, RobotMap.LIFT_UP_BUTTON);
	public final Button liftClosedButton = new JoystickButton(rightJoystick, RobotMap.LIFT_DOWN_BUTTON);
	public final Button pikeButton = new JoystickButton(leftJoystick, RobotMap.PIKE_BUTTON);
	public final Button revUpButton = new JoystickButton(leftJoystick, RobotMap.LAUNCHER_BUTTON);
	
	public final Button limitSwitchButton = new JoystickButton(leftJoystick, RobotMap.LIMIT_SWITCH_BUTTON);
}
