public class ExecutiveRoom extends Room{

    public ExecutiveRoom(String name, double roomPrice){
        super(name, roomPrice);
        setBasePrice(roomPrice);
    }

    public void setBasePrice(double price) {
        this.basePrice = price * 1.35;

        for (int i = 0; i < DPMList.size(); i++){

            DPMList.get(i).setBasePrice(this.basePrice);

        }
    }

}
