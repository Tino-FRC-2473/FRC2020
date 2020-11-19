public class FireShooter2Command extends SequentialCommandGroup {

    public FireShooter2Command(ShooterSubsystem shooterSubsystem, IntakeStorageSubsystem intakeStorageSubsystem) {
        

        addCommands(new InstantCommand(() -> intakeStorageSubsystem.runStorageMotor(-0.1)),
                     new WaitCommand(0.1),     
                    new InstantCommand(() -> intakeStorageSubsystem.runStorageMotor(0)), 
                    new RunShooterToRPMCommand(shooterSubsystem),
                    new InstantCommand(() -> intakeStorageSubsystem.runStorageMotor(0.3)),
                    new WaitCommand(1));
    }
}