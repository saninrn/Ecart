package api.service;

import api.entity.CatalogItemEntity;
import api.entity.OrderEntity;
import api.model.CatalogItem;
import api.model.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.repository.OrderStatusDao;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderStatusDao orderStatusDao;

    public Order getOrder(int orderId) {
        OrderEntity orderEntity = orderStatusDao.getOrder(orderId);
        Order order = null;
        if (orderEntity != null) {
            order = new Order();

            List<CatalogItem> itemList=new LinkedList<>();
            BeanUtils.copyProperties(orderEntity, order); //here extra fields of model shall have null value
            //override 'list' field of order, and populate itemlist of order. (note :ignore cyclic relationship while populating)
            for (CatalogItemEntity entity:orderEntity.getItems()) {
                CatalogItem catalogItem=new CatalogItem();
                catalogItem.setItemDescription(entity.getItemDescription());
                catalogItem.setItemId(entity.getItemId());
                catalogItem.setItemName(entity.getItemName());
                catalogItem.setManufacturer(entity.getManufacturer());
                catalogItem.setMaxRetailPrice(entity.getMaxRetailPrice());
                catalogItem.setRating(entity.getRating());
                catalogItem.setReviews(entity.getReviews());
                itemList.add(catalogItem);

            }
            order.setItems(itemList);
        }
        //if api.model null custom api.exception to be thrown
        return order;
    }
}
