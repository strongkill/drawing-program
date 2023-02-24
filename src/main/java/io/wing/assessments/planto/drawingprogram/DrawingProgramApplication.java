package io.wing.assessments.planto.drawingprogram;

import io.wing.assessments.planto.drawingprogram.canvas.CanvasExercise;
import io.wing.assessments.planto.drawingprogram.entity.HelpCommand;
import io.wing.assessments.planto.drawingprogram.service.CommandService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.io.IOException;

/**
 * technical assessment from planto
 * @author Wing K.Y
 *
 */
@SpringBootApplication(scanBasePackages = {"io.wing.assessments.planto.drawingprogram"})
public class DrawingProgramApplication implements CommandLineRunner {
	private static Logger LOG = LoggerFactory
			.getLogger(DrawingProgramApplication.class);

	@Resource
	CommandService commandService;


	public static void main(String[] args) {
		LOG.info("STARTING THE DrawingProgramApplication");
		SpringApplicationBuilder builder = new SpringApplicationBuilder(DrawingProgramApplication.class);
		builder.headless(false).run(args);
		LOG.info("DrawingProgramApplication FINISHED");
	}


	@Override
	public void run(String... args) throws IOException {
		LOG.info("EXECUTING : command line runner");
		HelpCommand.printHelp();

		try{
			new CanvasExercise(commandService);
//			Scanner sc= new Scanner(System.in);
//			while (true){
//				System.out.print("Enter your Command: ");
//				String command = sc.nextLine().trim();
//				exec.executeCommand(command);
//				BaseCommander cmd = RegExpTools.identifyCommand(command);
//				if(cmd!=null && cmd.valid()) {
//					cmd.excute();
//				}else{
//					BaseCommander.err(command);
//				}
//			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
