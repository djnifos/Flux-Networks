package sonar.fluxnetworks.common.network;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import sonar.fluxnetworks.api.misc.Coord4D;
import sonar.fluxnetworks.api.network.FluxLogicType;
import sonar.fluxnetworks.api.network.IFluxNetwork;
import sonar.fluxnetworks.api.device.IFluxDevice;
import sonar.fluxnetworks.common.connection.SimpleFluxDevice;
import sonar.fluxnetworks.common.connection.FluxNetworkCache;
import sonar.fluxnetworks.common.handler.PacketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConnectionUpdateRequestPacket extends AbstractPacket{

    public int networkID;
    public List<Coord4D> coords = new ArrayList<>();

    public ConnectionUpdateRequestPacket(PacketBuffer buf) {
        networkID = buf.readInt();
        int size = buf.readInt();
        for(int i = 0; i < size; i++) {
            coords.add(new Coord4D(buf));
        }
    }

    public ConnectionUpdateRequestPacket(int networkID, List<Coord4D> coords) {
        this.networkID = networkID;
        this.coords = coords;
    }
    @Override
    public void encode(PacketBuffer buf) {
        buf.writeInt(networkID);
        buf.writeInt(coords.size());
        coords.forEach(c -> c.write(buf));
    }

    @Override
    public Object handle(NetworkEvent.Context ctx) {
        if(coords.isEmpty()) {
            return null;
        }
        /*IFluxNetwork network = FluxNetworkCache.INSTANCE.getNetwork(networkID);
        if(network.isValid()) {
            PlayerEntity player = PacketHandler.getPlayer(ctx);
            List<CompoundNBT> tags = new ArrayList<>();
            List<IFluxDevice> onlineDevices = network.getConnections(FluxLogicType.ANY);
            coords.forEach(c -> onlineDevices.stream().filter(f -> f.getCoords().equals(c)).findFirst()
                            .ifPresent(f -> tags.add(SimpleFluxDevice.writeCustomNBT(f, new CompoundNBT()))));
            return new ConnectionUpdatePacket(networkID, tags);
        }*/
        return null;
    }
}
