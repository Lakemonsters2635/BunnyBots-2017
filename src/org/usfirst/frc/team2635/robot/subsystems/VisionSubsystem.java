package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.model.BucketVision;
import org.usfirst.frc.team2635.robot.model.Vision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The camera vision
 */
public class VisionSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public double MaxSensorDriveDistance = 120;
	
	Vision vision;
	BucketVision gearVision;
	UsbCamera camera;
	public boolean cameraIsConnected = false;
	public VisionSubsystem() {
		camera = CameraServer.getInstance().startAutomaticCapture();
		cameraIsConnected = camera.isConnected();

			
		
		//vision = new Vision(camera);
		
		//shooterVision = new ShooterVision(camera);
		//shooterVision.camInit();
		gearVision = new BucketVision(camera);
		gearVision.camInit();
		
	}
	
    
    
    public void ViewShooter(String message)
    {
    	gearVision.viewShooter(message);
    }
    public void gearAim() {

    	//gearVision.camInit();

		gearVision.createBox();
		gearVision.confirmBox();
		//gearVision.viewShooter(null);
    }
    
   
    
    
    
    public Double getAngleToGear() {
    	Double angle = gearVision.getAngle();
    	
    	return angle;
    }
    
    public Double getDistanceToGear() {
    	
    	Double distance = gearVision.getDistance();
    	if (distance != null && Math.abs(distance) > MaxSensorDriveDistance)
		{
			System.out.println("distance: " + distance);
			System.out.println("DISTANCE FROM SENSOR DATA EXCEEDS SAFTETY LIMIT. SETTING TO ZERO:" + distance);
			distance = null;
		}
    	
    	return distance;
    }
    
    public void saveShooter()
    {
    	gearVision.saveShooter();
    }
    
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}