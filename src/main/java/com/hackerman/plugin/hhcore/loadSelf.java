package com.hackerman.plugin.hhcore;

import com.eu.habbo.Emulator;
import com.eu.habbo.plugin.HabboPlugin;
import com.hackerman.plugin.hhcore.log.error;
import com.hackerman.plugin.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loadSelf {
    public static void loadSelf() {

        try (Connection connection = Emulator.getDatabase().getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM plugins WHERE plugin_id=?");
            statement.setString(1, main.pluginShort);

            PreparedStatement statement2 = connection.prepareStatement("INSERT INTO plugins VALUES (?, ?, ?, ?, ?, '1')");
            statement2.setString(1, main.pluginShort);
            statement2.setInt(2, main.productId);
            statement2.setString(3, main.version);
            statement2.setString(4, main.pluginAuthor);
            statement2.setString(5, main.pluginName);
            statement2.execute();

            statement2.close();
            statement2.getConnection().close();
        } catch (SQLException ex) {
            error.logError("loadSelf","loadSelf","", 2, true, ex);
        }

    }
}
