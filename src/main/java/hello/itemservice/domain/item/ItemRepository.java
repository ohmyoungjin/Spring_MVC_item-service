package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //static //실무에서는 Concurrent hasp map 사용해야함
    private static long sequence = 0L; //static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        //ArrayList로 감싸는 이유는 이후에 값을 넣거나 변경해도 store 객체에는 변경이 없어서 안전성이 확보 됨.
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        //프로젝트 규모가 커지면 받는 파라미터 DTO class를 만들어주는게 맞다.
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        //hash map data reset
        store.clear();
    }
}
