import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import ExampleClient from "../api/orderClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class AdminPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGetOne', 'onGetAll', 'onCreate', 'renderOrders', 'renderSingleOrder'], this);
        this.dataStore = new DataStore();
        //are states needed
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGetOne);
        document.getElementById('get-all-form').addEventListener('submit', this.onGetAll);
        document.getElementById('create-form').addEventListener('submit', this.onCreate);
        //do we need to create state?
        //this.datastore.addChangeListener(this.onStateChange);
        this.client = new OrderClient();

        const orders = await this.client.getAllOrders();
        if(orders && orders.length > 0){
        this.dataStore.set('orders', orders);
        }
    }
    // Render Methods --------------------------------------------------------------------------------------------------
    async renderSingleOrder() {
            let resultArea = document.getElementById("result-info");
            //not sure how datastore actaully works, using singular "order" may cause problems
            const order = this.dataStore.get("order");

            if (order) {
                resultArea.innerHTML = `
                    <div class="results">
                      <h4>${order.id}</h4>
                      <p>Date: ${order.orderDate}</p>
                      <p>Status: ${order.status}</p>
                      <p>ProductId: $(order.productId)</p>
                      <p>ProductId: $(order.customerName)</p>
                      <p>ProductId: $(order.customerAddress)</p>
                    </div>
                `
            } else {
                resultArea.innerHTML = "No Item";
            }
        }

    async renderOrders() {
        let resultArea = document.getElementById("result-info");

        const orders = this.dataStore.get("orders");
        if(orders){
           for(const order of orders){
           resultArea += `
              <div class="results">
                  <h4>${order.id}</h4>
                  <p>Date: ${order.orderDate}</p>
                  <p>Status: ${order.status}</p>
                  <p>ProductId: $(order.productId)</p>
                  <p>ProductId: $(order.customerName)</p>
                  <p>ProductId: $(order.customerAddress)</p>
              </div>
              `
           }
        }
        else {
            resultArea.innerHTML = "No Orders";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGetOne(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("id-field").value;
        this.dataStore.set("order", null);

        let result = await this.client.getOrder(id, this.errorHandler);
        this.dataStore.set("order", result);
        if (result) {
            this.showMessage(`Got ${result.id}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onGetAll(event) {
            // Prevent the page from refreshing on form submit
            event.preventDefault();

            this.dataStore.set("orders", null);

            let result = await this.client.getAllOrders(this.errorHandler);
            this.dataStore.set("orders", result);
            if (result) {
                this.showMessage(`Got ${result}!`)
            } else {
                this.errorHandler("Error doing GET!  Try again...");
            }
        }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("order", null);

        let product_id = document.getElementById("create-product-field").value;

        const createdOrder = await this.client.createExample(product_id, this.errorHandler);
        this.dataStore.set("order", createdOrder);

        if (createdExample) {
            this.showMessage(`Created ${createdExample.product_id}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const adminPage = new AdminPage();
    adminPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
