package org.usfirst.frc.team2635.robot.model;

import org.usfirst.frc.team2635.robot.Robot;
import org.usfirst.frc.team2635.robot.commands.ClampIn;
import org.usfirst.frc.team2635.robot.commands.ClampOut;
import org.usfirst.frc.team2635.robot.commands.LiftDown;
import org.usfirst.frc.team2635.robot.commands.LiftUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandGroupLibrary {
	
	public static CommandGroup lifterClosed() {
		CommandGroup lifterClosed = new CommandGroup();
		
    	lifterClosed.addSequential(new LiftDown(2));
    	lifterClosed.addSequential(new ClampIn(2));
    	lifterClosed.addSequential(new LiftUp(2));
   
		return lifterClosed;
	}
	public static CommandGroup lifterOpen() {
		CommandGroup lifterOpen = new CommandGroup();
		lifterOpen.addSequential(new ClampOut(2));
		return lifterOpen;
	}
	public static CommandGroup lifterInit() {
		CommandGroup lifterInit = new CommandGroup();
		lifterInit.addSequential(new LiftUp(2));
		lifterInit.addSequential(new ClampOut(2));
		
		return lifterInit;
	}
}
