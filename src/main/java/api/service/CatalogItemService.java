package api.service;

import api.entity.CatalogItemEntity;
import api.entity.OrderEntity;
import api.model.CatalogItem;
import api.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.repository.CatalogItemDAO;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class CatalogItemService {

    @Autowired
    private CatalogItemDAO catalogItemDAO;


    public List<CatalogItem> getAllItems() {


        List<CatalogItem> catalogItemList = new LinkedList<CatalogItem>();
        List<CatalogItemEntity> itemList = catalogItemDAO.getAllItems();
        if(itemList!=null) {
            for (CatalogItemEntity entity : itemList) {
                CatalogItem catalogItem = new CatalogItem();//should be created inside
                BeanUtils.copyProperties(entity, catalogItem); //entity to model mapper
                //overriding orderlist //ignore cyclic relationship
                List<Order>list=new LinkedList<>();
                for (OrderEntity orderEntity:entity.getOrders()) {
                    Order order=new Order();
                    order.setOrderId(orderEntity.getOrderId());
                    order.setRecipientAddress(orderEntity.getRecipientAddress());
                    order.setStatus(orderEntity.getStatus());
                    list.add(order);
                }
                catalogItem.setOrders(list); //list of order of each item
                catalogItemList.add(catalogItem);//list of items
            }
        }
//if list null: custom api.exception to be thrown
        return catalogItemList;
    }
}
