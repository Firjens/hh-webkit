package com.hackerman.plugin.hhcore;

import com.eu.habbo.Emulator;
import com.hackerman.plugin.hhcore.log.error;
import com.hackerman.plugin.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class disableSelf {
    public static void disableSelf() {
        try (Connection connection = Emulator.getDatabase().getDataSource().getConnection()) {
            PreparedStatement statement2 = connection.prepareStatement("UPDATE plugins SET status='0' WHERE plugin_id=?");
                statement2.setString(3, main.pluginShort);
            statement2.execute();

        } catch (SQLException ignored) {}
    }
}