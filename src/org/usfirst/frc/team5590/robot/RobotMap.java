/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {

    //Controllers
    public static final int DRIVER_CONTROLLER = 0;
    public static final int ASSISTANT_CONTROLLER = 1;

    //Drive Train
    public static final int LEFT_DRIVE_MOTOR = 7;
    public static final int RIGHT_DRIVE_MOTOR = 8;
        //Encoders
    public static final int[] LEFT_DRIVE_ENCODER = {3, 4};
    public static final int[] RIGHT_DRIVE_ENCODER = {5, 6};

    //Intake
    public static final int INTAKE_MOTOR = 3;
    public static final int MAGAZINE_MOTOR_1 = 4;
        //Magazine Encoder
    public static final int MAGAZINE_ENCODER[] = {1, 2};
        //Magazine Sensors
    public static final int MAGAZINE_SENSOR_1 = 0;
    public static final int MAGAZINE_SENSOR_2 = 1;
    public static final int MAGAZINE_SENSOR_3 = 2;

    //Shooter
    public static final int LEFT_SHOOT_MOTOR = 6;
    public static final int RIGHT_SHOOT_MOTOR = 6;
        //Pneumatics
        //Solenoids
    public static final int SHOOT_SOLENOID[] = {2, 3};
    // public static final int SHOOT_SOLENOID = 2;

    //Hanger
        //Motors
    public static final int REACH_MOTOR = 1;
    public static final int WINCH_MOTOR = 5;
        //Pneumatics
    public static final int REACH_SOLENOID[] = {0, 1};


}
