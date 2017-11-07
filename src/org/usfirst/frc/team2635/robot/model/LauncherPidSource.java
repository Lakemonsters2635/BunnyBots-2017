package org.usfirst.frc.team2635.robot.model;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class LauncherPidSource implements PIDSource{
	CANTalon launcher;
	public LauncherPidSource(CANTalon launcher) {
		this.launcher = launcher;
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		launcher.setPIDSourceType(pidSource);
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return launcher.getPIDSourceType();
	}

	@Override
	public double pidGet() {
		double speed = launcher.getSpeed();
		System.out.println("Speed: " + speed);
		return speed;
	}

}
