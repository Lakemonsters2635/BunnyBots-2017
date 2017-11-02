package org.usfirst.frc.team2635.robot.model;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDOutput;

public class LauncherPidOutput implements PIDOutput {
	CANTalon launcher;
	
	public LauncherPidOutput(CANTalon launcher) {
		this.launcher = launcher;
	}
	
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		launcher.set(output);
	}

}