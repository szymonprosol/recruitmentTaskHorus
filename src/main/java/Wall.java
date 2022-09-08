import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Wall implements Structure {

    private List<Block> blocks;

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return flattBlockList(blocks).stream()
                .filter(a -> a.getColor().equals(color))
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return flattBlockList(blocks).stream()
                .filter(a -> a.getMaterial().equals(material))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return flattBlockList(blocks).size();
    }

    public List<Block> flattBlockList(List<Block> list) {

        List<Block> result = new ArrayList<>();

        for (Block block : list) {

            if (block instanceof CompositeBlock) {
                result.add(block);
                flattBlockList(((CompositeBlock) block).getBlocks());
            } else {
                result.add(block);
            }
        }
        return result;
    }
}
