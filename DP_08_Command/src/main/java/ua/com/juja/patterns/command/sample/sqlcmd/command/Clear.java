package ua.com.juja.patterns.command.sample.sqlcmd.command;

import ua.com.juja.patterns.command.sample.sqlcmd.DatabaseManager;
import ua.com.juja.patterns.command.sample.sqlcmd.View;

/**
 * Created by oleksandr.baglai on 28.08.2015.
 */
public class Clear implements Command {

    private DatabaseManager manager;
    private View view;

    public Clear(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("clear|");
    }

    @Override
    public String description() {
        return "Очищение всей таблицы";
    }

    @Override
    public String format() {
        return "clear|tableName";
    }

    @Override
    public void process(String command) {
        String[] data = command.split("\\|");
        if (data.length != 2) {
            throw new IllegalArgumentException(
                    String.format("Формат команды '%s', а ты ввел: '%s'", format(), command));
        }
        manager.clear(data[1]);

        view.write(String.format("Таблица %s была успешно очищена.", data[1]));
    }
}
