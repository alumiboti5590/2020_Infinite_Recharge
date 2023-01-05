/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * Add your docs here.
 */
public class OI {

    public static XboxController driveController = new XboxController(RobotMap.DRIVER_CONTROLLER);
    public static XboxController assistantController = new XboxController(RobotMap.ASSISTANT_CONTROLLER);


    public OI(){
    }


        //DRIVER

    //Driver Sticks
    public double getDriverLeftStick(){
        return -driveController.getY(Hand.kLeft);
    }
    public double getDriverRightStick(){
        return -driveController.getY(Hand.kRight);
    }

    //Driver Triggers
    public double getDriverLeftTrigger(){
        return driveController.getTriggerAxis(Hand.kLeft);
    }
    public double getDriverRightTrigger(){
        return driveController.getTriggerAxis(Hand.kRight);
    }

    //Driver Bumpers
    public boolean getDriverLeftBumper(){
        return driveController.getBumper(Hand.kLeft);
    }
    public boolean getDriverRightBumper(){
        return driveController.getBumper(Hand.kRight);
    }


        //ASSISTANT
    
    //Assist Triggers
    public double getAssistLeftTrigger(){
        return assistantController.getTriggerAxis(Hand.kLeft);
    }
    public double getAssistRightTrigger(){
        return assistantController.getTriggerAxis(Hand.kRight);
    }

    //Assistant Bumpers
    public boolean getAssistLeftBumper(){
        return assistantController.getBumper(Hand.kLeft);
    }
    public boolean getAssistRightBumper(){
        return assistantController.getBumper(Hand.kRight);
    }

    //Assist Buttons
    public boolean isAssistBDown(){
        return assistantController.getBButton();
    }
    
    public boolean isAssistADown(){
        return assistantController.getRawButton(Button.kA.value);
    }
    public boolean isAssistXDown(){
        return assistantController.getXButton();
    }

    public boolean isAssistXReleased(){
        return assistantController.getXButtonReleased();
    }

    public boolean isAssistYDown(){
        return assistantController.getYButton();
    }

    public boolean isBackButtonDown(){
        return assistantController.getBackButton();
    }

    



}
