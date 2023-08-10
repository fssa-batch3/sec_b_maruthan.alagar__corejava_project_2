import in.fssa.mambilling.Exception.PersistanceException;
import in.fssa.mambilling.Exception.ServiceException;
import in.fssa.mambilling.Exception.ValidationException;
import in.fssa.mambilling.model.Price;
import in.fssa.mambilling.model.Product;
import in.fssa.mambilling.model.Product.QuantityType;
import in.fssa.mambilling.service.PriceService;
import in.fssa.mambilling.service.ProductService;

public class App {
	public static void main(String[] args) {

		ProductService ps = new ProductService();
		PriceService priceService = new PriceService();
		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Rice", 1000, QuantityType.g, "Pachai Rice", price);

		Price price1 = new Price(30, 0, 0);
		Product prod1 = new Product("Brown Sugar", 1000, QuantityType.g, "Nattu Sarkkarai", price1);

		Price price2 = new Price(20, 0, 0);
		Product prod2 = new Product("Cumin Powder", 100, QuantityType.g, null, price2);

		try {
			 ps.create(prod2);
			//	ps.update(prod1,2);
			// System.out.println(ps.getAll());
			ps.delete(4);
			System.out.println(priceService.getAll());
			// System.out.println(ps.getProductDetail(1));

		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}

	}
}
