package ir.ac.kntu;

public class Leader extends User{
    private TourLeader tourLeader;

    public Leader(TourLeader tourLeader){
        super();
        this.tourLeader = tourLeader;
        setUserState(UserState.Leader);
    }

    public TourLeader getTourLeader() {
        return tourLeader;
    }
}
