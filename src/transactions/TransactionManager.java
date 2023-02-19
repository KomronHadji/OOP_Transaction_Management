package transactions;

import java.util.*;

public class TransactionManager {
    Map<Region, String[]> regionStringMap = new HashMap<>();
    Map<Carrier, String[]> carrierStringMap = new HashMap<>();
    List<Request> requestList = new ArrayList<>();
    List<Offer> offerList = new ArrayList<>();
    List<Transaction> transactionList = new ArrayList<>();

    //R1
    public List<String> addRegion(String regionName, String... placeNames) {
//        List<String> placesList = new ArrayList<>();
//        Region region = new Region(regionName, placeNames);
//        regionStringMap.put(region, placeNames);
//        for (String placeName : placeNames) {
//            placesList.add(placeName);
//        }
        Region region = new Region(regionName, placeNames);
        regionStringMap.put(region, placeNames);
        return new ArrayList<>(Arrays.asList(placeNames));
    }

    public List<String> addCarrier(String carrierName, String... regionNames) {
        List<String> carrierRegionNames = new ArrayList<>();
//        for (Region region : regionStringMap.keySet()) {
//            if (region.getRegionName().equals(regionNames)) {
//                Carrier carrier = new Carrier(carrierName, regionNames);
//                carrierStringMap.put(carrier, regionNames);
//                carrierRegionNames.add(String.valueOf(regionNames));
//            }
//
//        }
        for (Region region : regionStringMap.keySet()) {

            Carrier carrier = new Carrier(carrierName, regionNames);
            carrierStringMap.put(carrier, regionNames);
            carrierRegionNames.add(Arrays.toString(regionNames));

        }

        return carrierRegionNames;
    }

    public List<String> getCarriersForRegion(String regionName) {
        List<String> carrierList = new ArrayList<>();
//        for (Carrier carrier : carrierStringMap.keySet()) {
//            if (carrier.getRegionName().equals(regionName)) {
//                carrierList.add(carrier.getCarrierName());
//            }
//        }
        for (Carrier carrier : carrierStringMap.keySet()) {
            carrierList.add(carrier.getCarrierName());

        }

        return carrierList;
    }

    //R2
    public void addRequest(String requestId, String placeName, String productId)
            throws TMException {
        Request request = new Request(requestId, placeName, productId);
        requestList.add(request);
    }

    public void addOffer(String offerId, String placeName, String productId)
            throws TMException {
        Offer offer = new Offer(offerId, placeName, productId);
        offerList.add(offer);
    }


    //R3
    public void addTransaction(String transactionId, String carrierName, String requestId, String offerId)
            throws TMException {
        Transaction transaction = new Transaction(transactionId, carrierName, requestId, offerId);
        transactionList.add(transaction);
    }

    public boolean evaluateTransaction(String transactionId, int score) {
        return false;
    }

    //R4
    public SortedMap<Long, List<String>> deliveryRegionsPerNT() {
        return new TreeMap<Long, List<String>>();
    }

    public SortedMap<String, Integer> scorePerCarrier(int minimumScore) {
        return new TreeMap<String, Integer>();
    }

    public SortedMap<String, Long> nTPerProduct() {
        return new TreeMap<String, Long>();
    }


}

