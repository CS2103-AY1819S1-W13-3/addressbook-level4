package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;


/**
 * Gets email address of person
 */

public class EmailCommand extends Command {

    public static final String COMMAND_WORD = "email";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Retrieves the email address of the student. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe";

    private final NameContainsKeywordsPredicate predicate;

    public EmailCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
}
