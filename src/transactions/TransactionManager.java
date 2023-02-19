package transactions;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionManager {
    private Map<Region, String[]> regionStringMap = new HashMap<>();
    private Map<Carrier, String[]> carrierStringMap = new HashMap<>();
    private List<Request> requestList = new ArrayList<>();
    private List<Offer> offerList = new ArrayList<>();
    private List<Transaction> transactionList = new ArrayList<>();

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

        return carrierRegionNames.stream().sorted().collect(Collectors.toList());
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

        return carrierList.stream().sorted().collect(Collectors.toList());
    }

    //R2
    public void addRequest(String requestId, String placeName, String productId)
            throws TMException {
        if (placeName == null) {
            throw new TMException("Name is null");
        }
        boolean placeNameExist = false;
        label:
        for (String[] values : regionStringMap.values()) {
            for (String value : values) {
                if (value.equals(placeName)) {
                    placeNameExist = true;
                    break label;
                }
            }
        }
        if (!placeNameExist) {
            throw new TMException("Undefined");
        }

        for (Request request : requestList) {
            if (request.getRequestId().equals(requestId)) {
                throw new TMException("Duplicate request ID");
            }
        }
        Request request = new Request(requestId, placeName, productId);
        requestList.add(request);
    }

    public void addOffer(String offerId, String placeName, String productId)
            throws TMException {
        if (placeName == null) {
            throw new TMException("PlaceName is null");
        }
        boolean placeExist = false;
        label:
        for (String[] values : regionStringMap.values()) {
            for (String value : values) {
                if (value.equals(placeName)) {
                    placeExist = true;
                    break label;
                }
            }
        }
        if (!placeExist) {
            throw new TMException("Undefined");
        }
        for (Offer offer : offerList) {
            if (offer.getOfferId().equals(offerId))
                throw new TMException("Duplicated offer ID");
        }
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

    static class sortbyName implements Comparator<Region> {

        @Override
        public int compare(Region o1, Region o2) {
            return o1.getRegionName().compareTo(o1.getRegionName());
        }
    }


}

