//import java.util.ArrayList;
//import java.util.List;
//
//import in.fssa.mambilling.Exception.PersistanceException;
//import in.fssa.mambilling.Exception.ServiceException;
//import in.fssa.mambilling.Exception.ValidationException;
//import in.fssa.mambilling.model.Price;
//import in.fssa.mambilling.model.Product;
//import in.fssa.mambilling.model.Product.QuantityType;
//import in.fssa.mambilling.model.User;
//import in.fssa.mambilling.service.BillService;
//import in.fssa.mambilling.service.PriceService;
//import in.fssa.mambilling.service.ProductService;
//import in.fssa.mambilling.service.UserService;
//
//public class App {
//	public static void main(String[] args) {
//
//		ProductService ps = new ProductService();
//		PriceService priceService = new PriceService();
//		UserService userservice = new UserService();
//		BillService billservice = new BillService();
//
//		Price price = new Price(1200, 1, 0);
//		Product prod = new Product("Rice", 1000, QuantityType.g, "Pachai Rice", price);
//
//		Price price1 = new Price(30, 0, 0);
//		Product prod1 = new Product("Brown Sugar", 1000, QuantityType.g, "Nattu Sarkkarai", price1);
//
//		Price price2 = new Price(20, 0, 0);
//		Product prod2 = new Product("Cumin Powder", 100, QuantityType.g, null, price2);
//
//		User newUser1 = new User("Maruthan", "maruthan@gmail.com", 7810061572l, "Pudukkottai");
//		User newUser2 = new User("SRV", null, 8810061572l, "Trichy");
//		User newUser3 = new User("Mathi", "mathi@gmail.com", 9810061572l, "Chennai");
//		User newUser4 = new User("Iam", "maililla@gmail.com", 9810061572l, "Karur");
//
//		List<Product> products = new ArrayList<Product>();
//		products.add(prod);
//		products.add(prod1);
//		products.add(prod2);
//
//		try {
//			// ps.create(prod2);
//			// ps.update(prod1,2);
//			// System.out.println(ps.getAll());
//			// ps.delete(4);
//
//			// System.out.println(ps.getProductDetail(1));
//
////			User newUser = new User("Maruthan","maruthan@gmail.com",8810061578l,"Pudukkottai");
////			
////			userservice.create(newUser);
//
////			System.out.println(userservice.findByPhoneNumber(7810061575l)); 
//
//			// System.out.println(userservice.getAllUsers());
//
//			userservice.update(9810061572l, newUser4);
//
////			billservice.create(1,products);
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		} catch (ValidationException e) {
//			e.printStackTrace();
//		}
//
//	}
//}
