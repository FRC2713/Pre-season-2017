package org.usfirst.frc.team2713.robot.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Command;

public class BackgroundFunctions extends Command {

	double timeLastRun = System.currentTimeMillis();
	ArrayList<Double> current;
	CANTalon toRecord;
	
	public BackgroundFunctions(CANTalon toRecord) {
		this.toRecord = toRecord;
		current = new ArrayList<Double>();
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		if(System.currentTimeMillis() - timeLastRun > 1000) {
			timeLastRun = System.currentTimeMillis();
			recordTalonData();
		}
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
	void recordTalonData() {
		current.add(toRecord.getOutputCurrent());
	}
	
	public void write() {
		File text = new File("talonFeed.txt");
		try {
			PrintWriter print = new PrintWriter(text);
			for(double x: current) {
				print.println(x + " " + "amps");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
