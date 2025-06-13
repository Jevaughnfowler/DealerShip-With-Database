package com.pluralsight.data;

import com.pluralsight.models.LeaseContract;
import java.util.List;

public interface LeaseContractDao {
    List<LeaseContract> getAllLeases();
    LeaseContract getLeaseById(int contractId);
    void addLease(LeaseContract lease);
    void removeLease(int contractId);
}