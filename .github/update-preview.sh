SNAPSHOT_MARKDOWN="![Snapshot](.github/snapshot.png)"
README_CONTENT=$(cat README.md)
UPDATED_README=$(echo "$README_CONTENT" | sed "/## Preview/a $SNAPSHOT_MARKDOWN")
echo "$UPDATED_README" > README.md
