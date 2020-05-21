package red.mohist.fabric;

import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import org.jetbrains.annotations.NotNull;

public class DedicatedServerImpl extends AbstractServerImpl {

    private MinecraftDedicatedServer server;

    public DedicatedServerImpl(MinecraftDedicatedServer server) {
        super(server);
        this.server = server;
    }

    @Override
    public int getViewDistance() {
        return server.getProperties().viewDistance;
    }

    @Override
    @NotNull
    public String getWorldType() {
        return server.getProperties().levelName.toUpperCase();
    }

    @Override
    public boolean getGenerateStructures() {
        // return server.getProperties().generateStructures;
        return false;
    }

    @Override
    public boolean getAllowNether() {
        return server.getProperties().allowNether;
    }

    @Override
    public boolean hasWhitelist() {
        return server.getProperties().whiteList.get();
    }

    @Override
    public void setWhitelist(boolean value) {
        server.setUseWhitelist(value);
    }

    /**
     * Gets the default ticks per water mob spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn water mobs
     *     every tick.
     * <li>A value of 400 will mean the server will attempt to spawn water mobs
     *     every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, water mobs spawning will be disabled.
     * <p>
     * Minecraft default: 1.
     *
     * @return the default ticks per water mobs spawn value
     */
    @Override
    public int getTicksPerWaterSpawns() {
        return 0;
    }

    /**
     * Gets the default ticks per ambient mob spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn ambient mobs
     *     every tick.
     * <li>A value of 400 will mean the server will attempt to spawn ambient mobs
     *     every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, ambient mobs spawning will be disabled.
     * <p>
     * Minecraft default: 1.
     *
     * @return the default ticks per ambient mobs spawn value
     */
    @Override
    public int getTicksPerAmbientSpawns() {
        return 0;
    }

    @Override
    public boolean getOnlineMode() {
        return server.getProperties().onlineMode;
    }

    @Override
    public boolean getAllowFlight() {
        return server.getProperties().allowFlight;
    }

    @Override
    public boolean isHardcore() {
        return server.getProperties().hardcore;
    }

    @Override
    public void shutdown() {
        // FIXME: just a placeholder!
        server.shutdown();
    }
}
