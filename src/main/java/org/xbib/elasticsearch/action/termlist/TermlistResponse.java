package org.xbib.elasticsearch.action.termlist;

import org.elasticsearch.action.ShardOperationFailedException;
import org.elasticsearch.action.support.broadcast.BroadcastOperationResponse;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.xbib.elasticsearch.common.termlist.CompactHashMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * A response for termlist action.
 */
public class TermlistResponse extends BroadcastOperationResponse {

    private int size;

    private Map<String, TermInfo> map;

    TermlistResponse() {
    }

    TermlistResponse(int totalShards, int successfulShards, int failedShards,
                     List<ShardOperationFailedException> shardFailures,
                     int size,
                     Map<String, TermInfo> map) {
        super(totalShards, successfulShards, failedShards, shardFailures);
        this.size = size;
        this.map = map;
    }

    public int getSize() {
        return size;
    }

    public Map<String, TermInfo> getTermlist() {
        return map;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        size = in.readInt();
        int n = in.readInt();
        map = new CompactHashMap<String, TermInfo>();
        for (int i = 0; i < n; i++) {
            String text = in.readString();
            TermInfo t = new TermInfo();
            t.readFrom(in);
            map.put(text, t);
        }
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeInt(size);
        out.writeInt(map.size());
        for (Map.Entry<String, TermInfo> t : map.entrySet()) {
            out.writeString(t.getKey());
            t.getValue().writeTo(out);
        }
    }
}