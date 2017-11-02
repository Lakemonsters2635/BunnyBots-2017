package org.usfirst.frc.team2635.robot.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BucketVision extends Vision {
	Rect confRect;
	ArrayList<Rect> reck1;
	
	Double confirmed;
	Integer welike;
	
	public BucketVision(UsbCamera camera){
		super(camera);
	}
	
	public void confirmBox(){
		Double[] possibilities = new Double[999];
		reck1 = new ArrayList<Rect>();
		
		confRect = null;
		for( Integer b = 0; b < boundRect.size(); b++ ){
			
				//Integer j = b;
				if (boundRect.get(b) != null){
				
					Rect rect1 = boundRect.get(b);
					
					Double height;
					Double width;

					//Post height of rectangles for debug
					SmartDashboard.putInt("rect1y",rect1.y);
					
					//Decide which rectangle is left or right
					
						height = (double) rect1.height;
						width = (double) rect1.width;
						
						//Bucket Height is   14.5 in
						//Bucket Width is    12   in
						
						//Uncomment to see what box is being tested
						//Imgproc.rectangle( source, rect1.tl(), rect1.br(), new Scalar(0,0,255), 2, 8, 0 );
						
					//Create variables to be used for confirmation
					
					//Do checks on rectangle pair
					Double comp1;
					
					comp1 = (height*6)/(width*7.25);
					
					//Post results of checks
					SmartDashboard.putDouble("comp1", comp1);

					if (rect1.height >15&&rect1.width>5&& .7 < comp1 && 1.3 > comp1 /*&&rect1.y>290&&rect2.y>290*/){
						
						for(int i=0;i<999;i++){
							if(possibilities[i]==null){
								possibilities[i]=comp1;
								reck1.add(rect1);
								i = 1005;
							}
						}
						}
					}
				}
			
		for (Integer i=0;i<possibilities.length;i++){
			
			if(i==0&&possibilities[i]!=null){
				confirmed = possibilities[i];
				welike = i;
			} else if(i==0&&possibilities[i]==null){
				confirmed = 0.0;
				welike = i;
			} else if(possibilities[i]!=null){
				if(1-Math.abs(possibilities[i]-1)>1-Math.abs(confirmed-1)){
					confirmed = possibilities[i];
					welike = i;
				}
				
			} else{
				break;
			}
		}
		if(welike!=null){
			//System.out.println("Target Found, welike:" +welike);
			if (welike < reck1.size()  ){
				Rect rect1 = reck1.get(welike);
				
				//Draw confirmed rectangles
				
				Imgproc.rectangle( source, rect1.tl(), rect1.br(), new Scalar(0,0,255), 2, 8, 0 );
				
				//Create new variables for correct boxes
				 
				
					
					confRect=rect1;
				
			}
		}
	}
	 
	public void viewShooter(String message){
   		//Draw Crosshairs
		Point line11 = new Point(0,240);
		Point line12 = new Point(620,240);
		Point line21 = new Point(320,0);
		Point line22 = new Point(320,480);
		Imgproc.line(source, line11, line12, new Scalar(255,255,255));
		Imgproc.line(source, line21, line22, new Scalar(255,255,255));
		Scalar scl = new Scalar(255,255,255);
		Imgproc.putText(source, message, new Point(10,300), 2, 1, scl);
		//put the processed image with rectangles on smartdashboard
		cvSource.putFrame(source);
	}
	public void saveShooter(){


		//Save Image
		try
		{
			currentdatehour = new SimpleDateFormat("MM_dd_yyy_HH_mm_ss_ms").format(new java.util.Date());
			Imgcodecs.imwrite("/home/admin/visionLog/image_"+currentdatehour+".jpg", source);
		}
        catch (Exception e)
		{
        	e.printStackTrace();
        } 
		finally 
		{
       
		}

		
	}
	
	public Double getDistanceBackup(){
		if(confRect == null)
		{
			return  null;
		}
		double targetWidthHeightRatio = 2.0/5.0;
		
		double fullYFOV = 41.8;
		double pixelHeight = 480;
		double halfYFOV = fullYFOV / 2;
		double distanceFromZero = 10.25;
		double cameraInclination = 6.8;
		//double cameraInclination = 7.8;
		//double cameraInclination = 8.5;
		//double cameraInclination = 8.8;
		
		
		//get y of middle of rect
		/*Point right = confRectTop.br();
		Point left = confRectTop.tl();
		double parthalf = right.y-left.y;
		parthalf = parthalf/2;
		double half = left.y + parthalf;
		
		double centerhalf =  half-240;
		half = Math.abs(half);
		double pixelRatioVerticle = centerhalf / (pixelHeight/2);
		double angle = halfFOV * pixelRatioVerticle;*/
		Point right = confRect.br();
		Point left  = confRect.tl();
		
		//get y of middle of rect 
		double rectangleHeight = Math.abs(left.y-right.y);
		double rectangleWidth  = Math.abs(left.x - right.x);
		
		
		double halfRectangleHeight = rectangleHeight/2;
		double RectangleCenterY = right.y + halfRectangleHeight;
		
		double verticalPixelsFromCameraCenterToRectangleCenter =  (pixelHeight/2) - RectangleCenterY;
		double pixelRatioVerticle = verticalPixelsFromCameraCenterToRectangleCenter / (pixelHeight/2);
		double angleFromCenterOfCameraToCenterOfTarget = halfYFOV * pixelRatioVerticle;
		double angle_Abs = Math.abs(angleFromCenterOfCameraToCenterOfTarget) - cameraInclination;
		//System.out.println("angle_Abs: " + angle_Abs);
		
		double angle_Radians = angle_Abs*Math.PI*2/360;
		
		double distance = distanceFromZero/Math.tan(angle_Radians);
		
		System.out.println("Gear Vision RectangeCenterY: " + RectangleCenterY);
		
		Double distanceDouble = new Double(distance);
		//TODO: get rid of linear reggression, and find issue with calculations 
		Double correctDistance = new Double(distanceDouble*1.219 + 6.193);
		return correctDistance;
	}
	
	public Double getDistance(){
		if(confRect == null )
		{
			return  null;
		}
		double minDistance = 28.17; //If the camera is closer we can't see the reflective tape.
		double pixelWidthAtMinDistance = 230.0; //The known Pixel-Width of the target at 28.17 inches.

		//Get the width in Pixels;
		double targetWidthInPixels = getTargetWidthInPixels();
		double resultDistance = 0;
		
		if (targetWidthInPixels > pixelWidthAtMinDistance)
		{
			System.out.println("------!!!TOO CLOSE!!!!--------------");
		}
		else
		{
			double ratio = pixelWidthAtMinDistance/targetWidthInPixels;
			resultDistance = minDistance * ratio;
		}
			
		return new Double(resultDistance);

	}
	
	public double getTargetWidthInPixels()
	{
		double minX = Math.min(confRect.br().x,  confRect.tl().x);
		minX = Math.min(minX, confRect.br().x);
		minX = Math.min(minX, confRect.tl().x);
		
		double maxX = Math.max(confRect.br().x,  confRect.tl().x);
		maxX = Math.max(maxX, confRect.br().x);
		maxX = Math.max(maxX, confRect.tl().x);
		double targetWidthInPixels = maxX - minX;
		
		return targetWidthInPixels;
	}
	
	public Double getAngle(){
		if(confRect == null)
		{
			return  null;
		}
		double fullXFOV = 53.14;
		double pixelWidth = 640;
		double centerPixelX = 320;
		double halfFOV = fullXFOV / 2;
		
		Point rightRectangleTopLeft = confRect.tl();
		
		Point rightRectBottomRight = confRect.br();
		
		
		//Point left = confRectRight.br();
		//Point right = confRectRight.tl();
		//Distance between leftRectangle.TopLeft, and rightRectangleBottomRight.
		
		double targetWidth = Math.abs(rightRectangleTopLeft.x - rightRectBottomRight.x);
		double targetXCenter = rightRectangleTopLeft.x + targetWidth/2;
		
		//double resultAngle = (targetXCenter - centerPixelX)/pixelWidth * fullXFOV;
		double angle = ((targetXCenter/pixelWidth)  - 0.5) * fullXFOV;
		
		return  new Double(angle);
				
		//get x of middle of rect 
//		double parthalf = rightRectBottomRight.x-rightRectangleTopLeft.x;
//		parthalf = parthalf/2;
//		double half = rightRectangleTopLeft.x + parthalf;
//		
//		double centerhalf =  half-(pixelWidth/2);
//		double pixelRatioHorizontal = centerhalf / (pixelWidth/2);
//		double angle = halfFOV * pixelRatioHorizontal;
//		
//		return  new Double(angle);
	}
}