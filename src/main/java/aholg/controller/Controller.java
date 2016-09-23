package aholg.controller;

import aholg.model.Canvas;
import aholg.model.Command;
import aholg.model.Command.Type;
import aholg.model.CommandParser;
import aholg.model.Line;
import aholg.model.NoCommandFoundException;
import aholg.model.Observer;
import aholg.model.Rectangle;

/**
 * This class takes requests from the view and calls the correct methods in the
 * model and in a safe order.
 * 
 * @author Anton
 *
 */
public class Controller {

	private CommandParser parser;
	private Canvas canvas;

	/**
	 * Constructor
	 * 
	 * @param observer
	 *            Observer object sent along to the model.
	 */
	public Controller() {

		parser = new CommandParser();

	}

	public String printCanvas() {
		if (canvas != null) {
			return canvas.printCanvas();
		}else{
			return "";
		}
	}

	/**
	 * Takes an input string to parse and create a new command.
	 * 
	 * @param input
	 *            Input String to parse.
	 * @throws Exception
	 *             Exception if unexpected null was returned from the parser
	 *             method.
	 */
	public void newCommand(String input) throws NoCommandFoundException, Exception {
		Command command = parseInput(input);
		if (command == null) {
			throw new NoCommandFoundException("Command not found");
		}
		Type type = command.getType();
		if (type == Type.CANVAS) {
			canvas = command.getCanvas();

			canvas.newCanvas();

		}
		if (canvas != null) {
			if (type == Type.LINE) {

				canvas.newLine((Line) command.getShape());

			} else if (type == Type.RECTANGLE) {

				canvas.newRectangle((Rectangle) command.getShape());

			} else if (type == Type.BUCKET) {

				canvas.colorFill(command.getColor());

			} else if (type == Type.QUIT) {
				System.exit(1);
			} else if (type == Type.HELP) {
				// observer.notify("Create canvas: C w h");
				// observer.notify("Create line: L x1 y1 x2 y2");
				// observer.notify("Create rectangle: R x1 y1 x2 y2");
				// observer.notify("Color area: B x y c");
				// observer.notify("Quit: Q");
				// return;
			} else if (type == Type.CLEAR) {
				canvas.clear();
			}
		} else {
			throw new Exception("Canvas needs to be created before drawing shapes.");
		}

	}

	/**
	 * Parses a string and creates a new command if match was found.
	 * 
	 * @param input
	 *            - Input to be parsed.
	 * @return - An object containing information about a command.
	 * @throws Exception
	 *             - Thrown if no matching command was found.
	 */
	private Command parseInput(String input) throws NoCommandFoundException, Exception {
		return parser.parseInput(input);
	}
}
