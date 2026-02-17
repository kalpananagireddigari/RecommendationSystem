import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class RecommendationSystem {

    public static void main(String[] args) {
        try {
            DataModel model = new FileDataModel(
                    new File("data/preferences.csv"));

            // Log-likelihood similarity (best for small datasets)
            ItemSimilarity similarity =
                    new LogLikelihoodSimilarity(model);

            GenericItemBasedRecommender recommender =
                    new GenericItemBasedRecommender(model, similarity);

            // Recommend items for User 1
            List<RecommendedItem> recommendations =
                    recommender.recommend(1, 5);

            System.out.println("Recommendations for User 1:");

            if (recommendations.isEmpty()) {
                System.out.println("No recommendations found.");
            } else {
                for (RecommendedItem item : recommendations) {
                    System.out.println(
                        "Item ID: " + item.getItemID() +
                        " | Score: " + item.getValue()
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
