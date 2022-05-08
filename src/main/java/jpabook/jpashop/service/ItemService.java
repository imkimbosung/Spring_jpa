package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

//    @Transactional
//    public void updateItem(Long itemId, Book param){
//        // 준 영속성이었던 Book 객체를 DB에서 findOne으로 찾아와 영속성으로 변경하여 set으로 변경만 해도 자동으로 update문을 날려준다.
//        // merge를 사용하면 전체 필드를 update하기 때문에 merge는 쓰지 않는것을 권장
//        Item findItem = itemRepository.findOne(itemId);
//        findItem.setPrice(param.getPrice());
//        findItem.setName(param.getName());
//        findItem.setStockQuantity(param.getStockQuantity());
//        //... 등등 set 생략
//        // 변경하는 시점에 set으로 나열하지말고 change같은 함수를 만들어서 직관적으로 알 수 있도록 한다.
//    }

    // 예시
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
