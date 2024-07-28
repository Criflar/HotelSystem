public class DeluxeRoom extends Room{
    
    public DeluxeRoom (String name, double roomPrice){
        super(name, roomPrice);
        setBasePrice(roomPrice);
    }

    public void setBasePrice(double price) {
        this.basePrice = price * 1.2;

        for (int i = 0; i < DPMList.size(); i++){

            DPMList.get(i).setBasePrice(this.basePrice);

        }
    }

}
