/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;

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
        return -driveController.getLeftY();
    }
    public double getDriverRightStick(){
        return -driveController.getRightY();
    }

    //Driver Triggers
    public double getDriverLeftTrigger(){
        return driveController.getLeftTriggerAxis();
    }
    public double getDriverRightTrigger(){
        return driveController.getRightTriggerAxis();
    }

    //Driver Bumpers
    public boolean getDriverLeftBumper(){
        return driveController.getLeftBumper();
    }
    public boolean getDriverRightBumper(){
        return driveController.getRightBumper();
    }


        //ASSISTANT
    
    //Assist Triggers
    public double getAssistLeftTrigger(){
        return assistantController.getLeftTriggerAxis();
    }
    public double getAssistRightTrigger(){
        return assistantController.getRightTriggerAxis();
    }

    //Assistant Bumpers
    public boolean getAssistLeftBumper(){
        return assistantController.getLeftBumper();
    }
    public boolean getAssistRightBumper(){
        return assistantController.getRightBumper();
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
