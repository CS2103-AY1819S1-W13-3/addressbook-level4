package seedu.address.logic.commands;

import java.util.List;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

/**
 * Extracts email address from person identified using it's displayed index from the list of students.
 */
public class EmailCommand extends Command {

    public static final String COMMAND_WORD = "email";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Prepares for email to be sent to email address of "
            + "the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ADDRESS_ADDED_SUCCESS = "Email address added: %1$s";
    private final Index targetIndex;

    public EmailCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEmail = lastShownList.get(targetIndex.getZeroBased());
        Email addressToEmail = personToEmail.getEmail();



        return new CommandResult(String.format(MESSAGE_ADDRESS_ADDED_SUCCESS, addressToEmail));
    }
}
