/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Capacitor;
import frc.robot.subsystems.*;
//import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.commands.TankDrive;
import frc.robot.commands.Shoot;
import frc.robot.commands.Reach;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  //Create Subsystems
  public static DriveTrain driveTrain; // = new DriveTrain(); BRIAN moved instantiations
  public static Intake intake; //= new Intake();
  public static Shooter shooter; //= new Shooter();
  public static Hanger hanger; // = new Hanger();
  public static Capacitor capacitor; //= new Capacitor();
  //public static TankDrive tankDrive = new TankDrive();
  public static OI oi = new OI();

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    intake = new Intake();
    shooter = new Shooter();
    hanger = new Hanger();
    //capacitor = new Capacitor();
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    //CameraServer.getInstance().startAutomaticCapture();

    // BRIAN default commands should be set here
    driveTrain.setDefaultCommand(new TankDrive(driveTrain)); 
    intake.setDefaultCommand(new Capacitor(intake));
    shooter.setDefaultCommand(new Shoot(shooter));
    hanger.setDefaultCommand(new Reach(hanger));
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    long autonomouseStart = System.currentTimeMillis();
 //   switch (m_autoSelected) {
 //     case kCustomAuto:
        // Put custom auto code here
        // Added by Brian S
        // Custom auto run until capacitor is empty
       /* while(!capacitor.totalEmpty()) {
          shooter.raiseShoot();
          intake.magIn();
          shooter.shootOut();
        }
        */
        long start = System.currentTimeMillis();
        long now = System.currentTimeMillis();
        while (now - start < 5000) 
        {
          now = System.currentTimeMillis();
          intake.magIn();
          shooter.raiseShoot();
          shooter.shootOut();
        }
        intake.stopMag();
        shooter.stopShoot();
        start = System.currentTimeMillis();
        now = System.currentTimeMillis();
        while (now-start < 2000)
        {
          now = System.currentTimeMillis();
          driveTrain.moveStraight(0.5);
         
        }
        driveTrain.leftDriveMotor.stopMotor();


        //stop until 15 seconds
        now = System.currentTimeMillis();
        while(now - autonomouseStart < 15000){
          now = System.currentTimeMillis();
        }

        //break;
        
        // TODO: Add code to drive backwards a few feet.
        
       // break;
 //     case kDefaultAuto:
 //     default:
        // Put default auto code here
 //       break;
      }
  
  
  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
     // driveTrain.runTest();
  }
}
