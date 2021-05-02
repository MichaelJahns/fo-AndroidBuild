package com.firstorion.project.ui.post

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstorion.project.R
import com.firstorion.project.network.PostApi
import com.firstorion.project.network.RetrofitInstance
import com.firstorion.project.repo.post.Post
import com.firstorion.project.repo.user.User
import com.firstorion.project.ui.user.UserFragment
import com.firstorion.project.viewmodel.post.PostsViewModelFactory
import com.firstorion.project.util.Toaster
import com.firstorion.project.viewmodel.post.PostsViewModel
import com.firstorion.project.viewmodel.user.UsersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostsFragment(
    private val usersViewModel: UsersViewModel
): Fragment(), PostsRVAdapter.OnPostClickedListener {
    private var mHandler: Handler = Handler(Looper.getMainLooper())
    private lateinit var postViewModel: PostsViewModel
    private lateinit var postsViewModelFactory: PostsViewModelFactory

    private lateinit var userFragment: UserFragment

    //    XML Views
    private lateinit var recyclerView: RecyclerView
    private lateinit var courierPost: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_posts, container, false)
        postsViewModelFactory = PostsViewModelFactory(
            requireActivity().application,
            PostApi(RetrofitInstance.postApi)
        )
        postViewModel =
            ViewModelProvider(this, postsViewModelFactory).get(PostsViewModel::class.java)
        userFragment = UserFragment(postViewModel, usersViewModel)
        initializeUI(view)
        setUpObservers()
        return view
    }

    private fun initializeUI(view: View) {
        recyclerView = view.findViewById(R.id.postsRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        courierPost = view.findViewById(R.id.courierMakePost)
        courierPost.setOnClickListener(View.OnClickListener {
            courierPost()
        })
        updateUI(listOf<Post>())
    }

    private fun updateUI(postList: List<Post>) {
        val adapter = PostsRVAdapter(this, postList)
        recyclerView.adapter = adapter
    }

    private fun setUpObservers() {
        postViewModel.getPosts().observe(viewLifecycleOwner, Observer { postList ->
            updateUI(postList)
        })
    }

    private fun courierPost() {
        val intent = Intent(context, CreatePostActivity::class.java)
        createPostContract.launch(intent)
    }

    private val createPostContract =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val title = result.data!!.getStringExtra("postTitle")
                val body = result.data!!.getStringExtra("postBody")
                val tempPost = Post(12, null, body!!, title!!)
                GlobalScope.launch(Dispatchers.IO) {
                    postViewModel.insertPost(tempPost)
                }
            } else if (result.resultCode == Activity.RESULT_CANCELED) {
                Toaster.makeToast(requireContext(), "Post Creation Cancelled")
            }
        }

    override fun onPostClicked(post: Post) {
        val bundle = Bundle()
        bundle.putInt("userId", post.userId)
        prepareTransition(post.userId)
        userFragment.arguments = bundle
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, userFragment)
            .addToBackStack(null)
            .commit()

        Toaster.makeToast(requireContext(), "Clicked upon post: ${post.postId}")
    }
    fun prepareTransition(userId: Int): LiveData<User> {
        return usersViewModel.getUserWithId(userId)
    }
}